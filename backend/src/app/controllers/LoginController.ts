import { Request, Response } from 'express'
import { inject, injectable } from 'inversify'
import { Rules } from 'validatorjs'

import ValidationMiddleware from '../middlewares/ValidationMiddleware'
import { LoginSocialUseCase } from '../useCases/@types'
import { LoginRequest, PostController } from './@types'

@injectable()
export class LoginController extends PostController {
  route = '/login'

  @inject('LoginValidator')
  private loginValidator: Rules

  @inject('LoginSocialUseCase')
  private loginSocialUseCase: LoginSocialUseCase

  async handle(req: Request, res: Response) {
    const { accessToken, social } = req.all as LoginRequest

    const jwtToken = await this.loginSocialUseCase.execute({
      accessToken,
      social
    })

    return res.json(jwtToken)
  }

  middlewares() {
    return [ValidationMiddleware(this.loginValidator)]
  }
}
