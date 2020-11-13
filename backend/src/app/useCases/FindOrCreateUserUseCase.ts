import { FacebookUser } from '~/app/providers/@types'
import { injectable, inject } from 'inversify'

import { User } from '../entities'
import { FindOrCreteUserParameter, FindOrCreteUserUseCase } from './@types'

@injectable()
export default class implements FindOrCreteUserUseCase {
  execute({ user }: FindOrCreteUserParameter) {
    return new User()
  }
}
