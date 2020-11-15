// eslint-disable-next-line @typescript-eslint/ban-types
export type TokenPayload = string | object | Buffer

export interface TokenProvider {
  sign(payload: TokenPayload): string
  verify<T>(token: string): T
}
