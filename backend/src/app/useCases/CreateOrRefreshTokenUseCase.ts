import { addMilliseconds, format } from 'date-fns'
import { inject, injectable } from 'inversify'
import ms from 'ms'
import { Raw } from 'typeorm'

import { Token } from '../entities'
import { TokenType } from '../entities/Token'
import { TokenProvider } from '../providers/@types'
import {
  AccessToken,
  CreateOrRefreshTokenParameter,
  CreateOrRefreshTokenUseCase
} from './@types'

@injectable()
export default class implements CreateOrRefreshTokenUseCase {
  @inject('TokenProvider')
  private tokenProvider: TokenProvider

  private expiresIn: number

  constructor(@inject('JWT_EXPIRES_IN') expiresIn: string) {
    this.expiresIn = ms(expiresIn)
  }

  async execute({ user }: CreateOrRefreshTokenParameter): Promise<AccessToken> {
    let token = await Token.findOne({
      where: {
        user: user,
        expiresAt: Raw(
          alias => `${alias} > '${format(new Date(), 'yyyy-MM-dd HH:mm:ss')}'`
        ),
        type: TokenType.LoggedUser
      }
    })

    if (!token) {
      token = new Token()
      const jwtToken = this.tokenProvider.sign({ id: user.id })

      token.key = jwtToken
    } else {
      const newToken = this.tokenProvider.refresh(token.key)
      await token.remove()
      token.key = newToken
    }

    token.type = TokenType.LoggedUser
    token.expiresAt = addMilliseconds(new Date(), this.expiresIn)
    token.user = user

    await token.save()

    return {
      token: token.key,
      type: 'Bearer',
      expiresAt: token.expiresAt
    }
  }
}
