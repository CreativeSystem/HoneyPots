import { SocialType } from '~/app/useCases/@types'

export interface LoginRequest {
  accessToken: string
  tokenId: string
  social: SocialType
}
