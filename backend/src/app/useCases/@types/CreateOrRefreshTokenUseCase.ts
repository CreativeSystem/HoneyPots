import { User } from '~/app/entities'

import { UseCase } from '.'

export interface AccessToken {
  type: string
  token: string
  expiresAt: Date
}

export interface CreateOrRefreshTokenParameter {
  user: User
}

export type CreateOrRefreshTokenUseCase = UseCase<
  CreateOrRefreshTokenParameter,
  AccessToken
>
