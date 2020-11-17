// eslint-disable-next-line @typescript-eslint/ban-types
export type TokenPayload = string | object | Buffer
export type TokenVerify = {
  iat?: number
  exp?: number
}

export interface TokenProvider {
  sign(payload: TokenPayload): string
  verify<T>(token: string): T
  refresh(token: string): string
}
