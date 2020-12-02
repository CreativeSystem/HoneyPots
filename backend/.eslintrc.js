module.exports = {
  env: {
    browser: true,
    es2020: true,
    node: true,
    jest: true
  },
  extends: [
    'standard',
    'plugin:@typescript-eslint/recommended',
    'prettier/@typescript-eslint',
    'prettier/standard'
  ],
  parser: '@typescript-eslint/parser',
  parserOptions: {
    ecmaFeatures: {
      jsx: true
    },
    ecmaVersion: 11,
    sourceType: 'module'
  },
  plugins: ['@typescript-eslint', 'prettier', 'eslint-plugin-import-helpers'],
  rules: {
    'max-len': ['error', { code: 100 }],
    'prettier/prettier': 'error',
    '@typescript-eslint/explicit-module-boundary-types': 'off',
    'import-helpers/order-imports': [
      'warn',
      {
        // example configuration
        newlinesBetween: 'always',
        groups: [
          'module',
          '/^@shared/',
          '/^@/',
          ['parent', 'sibling', 'index']
        ],
        alphabetize: { order: 'asc', ignoreCase: true }
      }
    ],
    'space-before-function-paren': 'off',
    '@typescript-eslint/no-explicit-any': 'off',
    camelcase: 'off',
    'no-useless-call': 'off',
    indent: 'off'
  },
  settings: {
    'import/resolver': {
      typescript: {}
    }
  }
}
