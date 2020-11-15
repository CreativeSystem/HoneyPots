import { User } from '~/app/entities'
import { FacebookUser, GoogleUser } from '~/app/providers/@types'

import { UseCase } from '.'

export interface FindOrCreateUserParameter {
  user: FacebookUser | GoogleUser
}

export type FindOrCreateUserUseCase = UseCase<FindOrCreateUserParameter, User>
