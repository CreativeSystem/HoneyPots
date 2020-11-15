import { GoogleUser } from '~/app/providers/@types'

import { UseCase } from '.'

export interface GetUserGoogleParameter {
  accessToken: string
  tokenId: string
}

export type GetUserGoogleUseCase = UseCase<GetUserGoogleParameter, GoogleUser>
