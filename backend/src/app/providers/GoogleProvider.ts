import axios, { AxiosInstance } from 'axios'
import { inject, injectable } from 'inversify'

import { GoogleProvider, GoogleToken, GoogleUser } from './@types'

@injectable()
export default class implements GoogleProvider {
  private client: AxiosInstance
  @inject('GOOGLE_CLIENT_ID')
  private googleClientId: string

  @inject('GOOGLE_CLIENT_SECRET')
  private googleClientSecret: string

  constructor(@inject('GOOGLE_API_URL') googleApiUrl: string) {
    this.client = axios.create({
      baseURL: googleApiUrl
    })
  }

  async getMe(code: string): Promise<GoogleUser> {
    const {
      data: { access_token: accessToken, id_token: tokenId }
    } = await this.client.post<GoogleToken>('/token', null, {
      params: {
        code,
        client_id: this.googleClientId,
        client_secret: this.googleClientSecret,
        grant_type: 'authorization_code',
        redirect_uri: 'http://localhost:8080/google/auth'
      },
      baseURL: 'https://oauth2.googleapis.com'
    })

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
