import { User } from '~/app/entities'
import { FacebookUser } from '~/app/providers/@types'

import { UseCase } from '.'

export interface FindOrCreteUserParameter {
  user: FacebookUser
}

export type FindOrCreteUserUseCase = UseCase<FindOrCreteUserParameter, User>
