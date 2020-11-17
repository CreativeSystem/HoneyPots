import axios, { AxiosInstance } from 'axios'
import { inject, injectable } from 'inversify'

import { FacebookProvider, FacebookUser } from './@types'

@injectable()
export default class implements FacebookProvider {
  private client: AxiosInstance

  constructor(@inject('FACEBOOK_GRAPH_URL') facebookGraphUrl: string) {
    this.client = axios.create({
      baseURL: facebookGraphUrl
    })
  }

  async getMe(accessToken: string): Promise<FacebookUser> {
    const fields = ['id', 'picture.type(large)', 'name', 'email']
    const { data } = await this.client.get<FacebookUser>('/me', {
      params: {
        access_token: accessToken,
        fields: fields.join(',')
      }
    })

    return data
  }
}
