import container from '~/config/inversify'
import { Request, Response } from 'express'
import { inject, injectable } from 'inversify'
import { Rules } from 'validatorjs'

import ValidationMiddleware from '../middlewares/ValidationMiddleware'
import { GetUserFacebookUseCase } from '../useCases/@types'
import { LoginRequest, PostController } from './@types'

@injectable()
export class LoginController extends PostController {
  route = '/login'

  @inject('LoginValidator')
  private loginValidator: Rules

  @inject('GetUserFacebookUseCase')
  private getUserFacebookUseCase: GetUserFacebookUseCase

  async handle(req: Request, res: Response) {
    const { accessToken } = req.all as LoginRequest
    res.json(await this.getUserFacebookUseCase.execute({ accessToken }))
  }

  middlewares() {
    return [ValidationMiddleware(this.loginValidator)]
  }
}
