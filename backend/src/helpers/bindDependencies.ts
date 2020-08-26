import inversifyContainer from '~/config/inversify'

export default function bindDependencies(func, dependencies) {
  const injections = dependencies.map(dependency => {
    return inversifyContainer.get(dependency)
  })
  return func.bind(func, ...injections)
}
