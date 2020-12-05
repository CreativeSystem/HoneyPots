import { inject, injectable } from 'inversify'

import { GoogleProvider } from '../providers/@types'
import { GetUserGoogleParameter, GetUserGoogleUseCase } from './@types'

@injectable()
export default class implements GetUserGoogleUseCase {
  @inject('GoogleProvider')
  private googleProvider: GoogleProvider

  async execute({ accessToken }: GetUserGoogleParameter) {
    return await this.googleProvider.getMe(accessToken)
  }
}
