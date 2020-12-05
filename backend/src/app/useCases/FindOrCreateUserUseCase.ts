import downloadImageFromUrl from '~/helpers/downloadImageFromUrl'
import { injectable } from 'inversify'
import { v4 as uuid } from 'uuid'

import { User, Image } from '../entities'
import { FacebookPicture } from '../providers/@types'
import {
  FindOrCreateUserParameter,
  FindOrCreateUserUseCase,
  SocialType
} from './@types'

@injectable()
export default class implements FindOrCreateUserUseCase {
  async execute({ user: socialUser, social }: FindOrCreateUserParameter) {
    const isFacebook = social === SocialType.FACEBOOK
    const isGoogle = social === SocialType.GOOGLE
    let user = await User.findOne(
      isFacebook
        ? {
            where: [
              {
                facebookId: socialUser.id
              },
              {
                email: socialUser.email
              }
            ]
          }
        : {
            where: [
              {
                googleId: socialUser.id
              },
              {
                email: socialUser.email
              }
            ]
          }
    )

    if (!user) {
      user = new User()
      user.id = uuid()
    }
    const avatarId = uuid()
    const avatarName = `${avatarId}.png`
    const avatarUrl = isGoogle
      ? (socialUser.picture as string)
      : (socialUser.picture as FacebookPicture).data.url

    await downloadImageFromUrl(
      avatarUrl,
      `${process.env.IMAGE_DIR}/${avatarName}`
    )
    const image = Image.create({
      id: avatarId,
      name: avatarName,
      originalName: avatarName
    })

    await image.save()

    user.name = socialUser.name
    user.googleId = isGoogle ? socialUser.id : user.googleId
    user.facebookId = isFacebook ? socialUser.id : user.facebookId
    user.email = socialUser.email
    user.avatarUrl = isGoogle
      ? (socialUser.picture as string)
      : (socialUser.picture as FacebookPicture).data.url

    return await user.save()
  }
}
