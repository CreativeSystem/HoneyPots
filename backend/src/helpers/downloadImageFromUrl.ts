import fs from 'fs'
import request from 'request'

export default function (imageUrl: string, pathToSave: string) {
  return new Promise(resolve => {
    request(imageUrl)
      .pipe(fs.createWriteStream(pathToSave))
      .on('close', resolve)
  })
}
