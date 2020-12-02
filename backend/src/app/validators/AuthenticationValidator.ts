import createValidatorRules from '~/helpers/createValidatorRules'

export default createValidatorRules({
  authorization: ['required', 'string']
})
