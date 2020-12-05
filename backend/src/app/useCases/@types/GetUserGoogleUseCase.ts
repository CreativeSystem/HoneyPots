import { GoogleUser } from '~/app/providers/@types'

import { UseCase } from '.'

export interface GetUserGoogleParameter {
  accessToken: string
}

export type GetUserGoogleUseCase = UseCase<GetUserGoogleParameter, GoogleUser>
