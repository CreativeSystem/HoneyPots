import { User } from '~/app/entities'

import { UseCase } from '.'

export interface AccessToken {
  type: string
  token: string
  expiresAt: Date
}

export interface CreateTokenParameter {
  user: User
}

export type CreateTokenUseCase = UseCase<CreateTokenParameter, AccessToken>
