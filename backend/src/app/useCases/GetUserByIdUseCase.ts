import { injectable } from 'inversify'

import { User } from '../entities'
import { GetUserByIdUseCase } from './@types'

@injectable()
export default class implements GetUserByIdUseCase {
  async execute(id: string) {
    const user = await User.findOneOrFail({
      where: {
        id
      }
    })

    return user
  }
}
