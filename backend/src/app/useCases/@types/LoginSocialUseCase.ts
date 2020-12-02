import { AccessToken, UseCase } from '.'

export enum SocialType {
  FACEBOOK = 'facebook',
  GOOGLE = 'google'
}

export interface LoginSocialParameter {
  tokenId: string
  accessToken: string
  social: SocialType
}

export type LoginSocialUseCase = UseCase<LoginSocialParameter, AccessToken>
