import { FacebookUser } from '~/app/providers/@types'

import { UseCase } from '.'

export interface GetUserFacebookParameter {
  accessToken: string
}

export type GetUserFacebookUseCase = UseCase<
  GetUserFacebookParameter,
  FacebookUser
>
