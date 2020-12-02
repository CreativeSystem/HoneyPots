import { User } from '~/app/entities'

import { UseCase } from '.'

export type GetUserByIdUseCase = UseCase<string, User>
