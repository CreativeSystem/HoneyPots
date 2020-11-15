export interface FacebookProvider {
  getMe(accessToken: string): Promise<FacebookUser>
}

export interface FacebookUser {
  type: 'facebook'
  id: string
  name: string
  email: string
  picture: FacebookPicture
}

export interface FacebookPicture {
  data: {
    height: number
    width: number
    url: string
  }
}
