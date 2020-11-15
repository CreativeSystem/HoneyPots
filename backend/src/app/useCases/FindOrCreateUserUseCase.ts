import { injectable } from 'inversify'
import { v4 as uuid } from 'uuid'

import { User } from '../entities'
import { FindOrCreateUserParameter, FindOrCreateUserUseCase } from './@types'

@injectable()
export default class implements FindOrCreateUserUseCase {
  async execute({ user: searchUser }: FindOrCreateUserParameter) {
    let user: User | undefined

    if (searchUser.type === 'facebook') {
      user = await User.findOne({
        where: [
          {
            facebookId: searchUser.id
          },
          {
            email: searchUser.email
          }
        ]
      })
    } else {
      user = await User.findOne({
        where: [
          {
            googleId: searchUser.id
          },
          {
            email: searchUser.email
          }
        ]
      })
    }
    if (!user) {
      user = new User()
      user.id = uuid()
    }

    user.name = searchUser.name
    user.googleId = searchUser.type === 'google' ? searchUser.id : user.googleId
    user.facebookId =
      searchUser.type === 'facebook' ? searchUser.id : user.facebookId
    user.email = searchUser.email
    user.avatarUrl =
      searchUser.type === 'google'
        ? searchUser.picture
        : searchUser.picture.data.url

    return await user.save()
  }
}
