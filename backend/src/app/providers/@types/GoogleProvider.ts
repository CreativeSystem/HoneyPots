export interface GoogleProvider {
  getMe(accessToken: string): Promise<GoogleUser>
}

export interface GoogleToken {
  id_token: string
  access_token: string
}

export interface GoogleUser {
  id: string
  name: string
  email: string
  picture: string
}
