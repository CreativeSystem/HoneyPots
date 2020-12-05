import fs from 'fs'
import { promisify } from 'util'

export default async function (path: string, base64: string) {
  const base64Data = base64
    .replace(/^data:image\/png;base64,/, '')
    .replace(/^data:image\/jpeg;base64,/, '')

  await promisify(fs.writeFile)(path, base64Data, 'base64')
}
