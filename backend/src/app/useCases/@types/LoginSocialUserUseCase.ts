import { UseCase } from '.'

export enum SocialType {
  FACEBOOK = 'facebook',
  GOOGLE = 'google'
}

export interface LoginSocialUser {
  accessToken: string
  social: SocialType
}

export type LoginSocialUserUseCase = UseCase<SocialType>
