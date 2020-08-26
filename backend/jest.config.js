// eslint-disable-next-line @typescript-eslint/no-var-requires
const { name } = require('./package.json')

module.exports = {
  displayName: name,
  name,
  clearMocks: true,
  preset: 'ts-jest',
  testEnvironment: 'node',
  testMatch: [
    '**/__tests__/**/*.spec.[jt]s?(x)',
    '!**/__tests__/coverage/**',
    '!**/__tests__/utils/**'
  ],
  moduleNameMapper: {
    '~/(.*)': '<rootDir>/src/$1',
    '@config/(.*)': '<rootDir>/src/config/$1',
    '@app/(.*)': '<rootDir>/src/app/$1',
    '@helpers/(.*)': '<rootDir>/src/helpers/$1'
  },
  testTimeout: 20000
}
