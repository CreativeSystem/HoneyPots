export interface UseCase<P = any, R = void> {
  execute(parameter: P): R | Promise<R>
}
