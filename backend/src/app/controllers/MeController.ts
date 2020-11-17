import { Request, Response } from 'express'
import { inject, injectable } from 'inversify'

import { GetUserByIdUseCase } from '../useCases/@types'
import { GetController } from './@types'

@injectable()
export class MeController extends GetController {
  route = '/me'
  authentication = true

  @inject('GetUserByIdUseCase')
  private getUserByIdUseCase: GetUserByIdUseCase

  async handle(req: Request, res: Response) {
    const {
      id,
      name,
      email,
      avatarUrl,
      configuration
    } = await this.getUserByIdUseCase.execute(req.user)

    return res.json({
      id,
      name,
      email,
      avatarUrl,
      configuration
    })
  }
}
