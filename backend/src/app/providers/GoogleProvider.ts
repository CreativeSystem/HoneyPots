import axios, { AxiosInstance } from 'axios'
import { inject, injectable } from 'inversify'

import { GoogleProvider, GoogleUser } from './@types'

@injectable()
export default class implements GoogleProvider {
  private client: AxiosInstance

  constructor(@inject('GOOGLE_API_URL') googleApiUrl: string) {
    this.client = axios.create({
      baseURL: googleApiUrl
    })
  }

  async getMe(accessToken: string, tokenId: string): Promise<GoogleUser> {
    const { data } = await this.client.get<GoogleUser>('/oauth2/v1/userinfo', {
      params: {
        access_token: accessToken,
        alt: 'json'
      },
      headers: {
        Authorization: `Bearer ${tokenId}`
      }
    })
    return data
  }
}
