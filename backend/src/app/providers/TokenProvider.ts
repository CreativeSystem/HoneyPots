import fs from 'fs'
import { inject, injectable } from 'inversify'
import jwt from 'jsonwebtoken'

import { TokenPayload, TokenProvider } from './@types'
@injectable()
export default class implements TokenProvider {
  private privateKey: Buffer
  private publicKey: Buffer
  @inject('JWT_EXPIRES_IN')
  private expiresIn: string

  constructor(
    @inject('JWT_PRIVATE_KEY_PATH') privateKeyPath: string,
    @inject('JWT_PUBLIC_KEY_PATH') publicKeyPath: string
  ) {
    this.privateKey = fs.readFileSync(privateKeyPath)
    this.publicKey = fs.readFileSync(publicKeyPath)
  }

  sign(payload: TokenPayload): string {
    return jwt.sign(payload, this.privateKey, {
      algorithm: 'RS256',
      expiresIn: this.expiresIn
    })
  }

  verify<T>(token: string): T {
    return (jwt.verify(token, this.publicKey, {
      algorithms: ['RS256']
    }) as unknown) as T
  }
}
