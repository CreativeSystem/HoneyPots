import { Request, Response } from 'express'
import { injectable } from 'inversify'
import path from 'path'

import { GetController } from './@types'

@injectable()
export class ImageController extends GetController {
  route = '/image/:id'

  async handle(req: Request, res: Response) {
    const image = `${process.env.IMAGE_DIR}/${req.params.id}.png`

    return res.sendFile(path.resolve(image))
  }
}
