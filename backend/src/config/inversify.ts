import 'reflect-metadata'

import { Container } from 'inversify'

const inversifyContainer = new Container({
  autoBindInjectable: true
})

export default inversifyContainer
