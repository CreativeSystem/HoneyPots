export interface GoogleProvider {
  getMe(accessToken: string, tokenId: string): Promise<GoogleUser>
}

export interface GoogleUser {
  type: 'google'
  id: string
  name: string
  email: string
  picture: string
}
