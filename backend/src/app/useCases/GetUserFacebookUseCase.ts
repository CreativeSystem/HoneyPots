import { inject, injectable } from 'inversify'

import { FacebookProvider } from '../providers/@types'
import { GetUserFacebookParameter, GetUserFacebookUseCase } from './@types'

@injectable()
export default class implements GetUserFacebookUseCase {
  @inject('FacebookProvider')
  private facebookProvider: FacebookProvider

  async execute({ accessToken }: GetUserFacebookParameter) {
    return await this.facebookProvider.getMe(accessToken)
  }
}
