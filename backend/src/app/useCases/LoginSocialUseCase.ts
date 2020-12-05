import { inject, injectable } from 'inversify'

import { CreateOrRefreshTokenUseCase, FindOrCreateUserUseCase } from '.'
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

  @inject('CreateOrRefreshTokenUseCase')
  private createTokenUseCase: CreateOrRefreshTokenUseCase

  async execute({ social, accessToken }: LoginSocialParameter) {
    const socialUser = await (social === SocialType.FACEBOOK
      ? this.getUserFacebookUseCase.execute({ accessToken })
      : this.getUserGoogleUseCase.execute({ accessToken }))

    const user = await this.findOrCreateUserUseCase.execute({
      user: socialUser,
      social
    })

    return await this.createTokenUseCase.execute({ user })
  }
}
