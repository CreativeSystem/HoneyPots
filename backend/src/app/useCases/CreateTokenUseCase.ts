import { addMilliseconds } from 'date-fns'
import { inject, injectable } from 'inversify'
import jwt from 'jsonwebtoken'
import ms from 'ms'

import { Token } from '../entities'
import { TokenType } from '../entities/Token'
import { TokenProvider } from '../providers/@types'
import { AccessToken, CreateTokenParameter, CreateTokenUseCase } from './@types'

@injectable()
export default class implements CreateTokenUseCase {
  @inject('TokenProvider')
  private tokenProvider: TokenProvider

  private expiresIn: number

  constructor(@inject('JWT_EXPIRES_IN') expiresIn: string) {
    this.expiresIn = ms(expiresIn)
  }

  async execute({ user }: CreateTokenParameter): Promise<AccessToken> {
    // TODO verificar se existe token ativo
    // - se existir realizar refresh no token
    // - se nao, criar novo
    const jwtToken = this.tokenProvider.sign({ id: user.id })

    const token = new Token()
    token.key = jwtToken
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
