import { Rules, TypeCheckingRule } from 'validatorjs'

type ValidatorRules<T> = {
  [P in keyof T]?:
    | string
    | Array<string | TypeCheckingRule>
    | ValidatorRules<T[P]>
}

export default function <T = any>(rules: ValidatorRules<T>) {
  return rules as Rules
}
