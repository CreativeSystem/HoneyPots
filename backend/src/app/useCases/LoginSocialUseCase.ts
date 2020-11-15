import { inject, injectable } from 'inversify'

import { CreateTokenUseCase, FindOrCreateUserUseCase } from '.'
import {
  GetUserFacebookUseCase,
  GetUserGoogleUseCase,
  LoginSocialParameter,
  LoginSocialUseCase,
  SocialType
} from './@types'

@injectable()
export default class implements LoginSocialUseCase {
  @inject('GetUserFacebookUseCase')
  private getUserFacebookUseCase: GetUserFacebookUseCase

  @inject('GetUserGoogleUseCase')
  private getUserGoogleUseCase: GetUserGoogleUseCase

  @inject('FindOrCreateUserUseCase')
  private findOrCreateUserUseCase: FindOrCreateUserUseCase

  @inject('CreateTokenUseCase')
  private createTokenUseCase: CreateTokenUseCase

  async execute({ social, accessToken, tokenId }: LoginSocialParameter) {
    const socialUser = await (social === SocialType.FACEBOOK
      ? this.getUserFacebookUseCase.execute({ accessToken })
      : this.getUserGoogleUseCase.execute({ accessToken, tokenId }))

    const user = await this.findOrCreateUserUseCase.execute({
      user: socialUser
    })

    return await this.createTokenUseCase.execute({ user })
  }
}
