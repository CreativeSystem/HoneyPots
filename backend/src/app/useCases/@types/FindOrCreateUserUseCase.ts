import { User } from '~/app/entities'
import { FacebookUser, GoogleUser } from '~/app/providers/@types'

import { UseCase } from '.'
import { SocialType } from './LoginSocialUseCase'

export interface FindOrCreateUserParameter {
  user: FacebookUser | GoogleUser
  social: SocialType
}

export type FindOrCreateUserUseCase = UseCase<FindOrCreateUserParameter, User>
