import { Request, Response } from 'express'
import { injectable } from 'inversify'
import { getManager, In } from 'typeorm'

import { Recipe } from '../entities'
import { GetController } from './@types'
interface RecipeRequest {
  search?: string
}

@injectable()
export class RecipeController extends GetController {
  route = '/recipe'
  authentication = true

  async handle(req: Request, res: Response) {
    const { search } = req.all as RecipeRequest

    const recipesId = (await getManager().query(
      `
      with search_ as (
        select r.id,
        r.content->>'name' || ' ' || 
        array_to_string(ARRAY(SELECT jsonb_array_elements_text(r."content"->'types')),' ') 
        as search_text 
        from recipe r
      )
      select s.id
      from search_ s
      where s.search_text ilike '%'|| $1 ||'%'
    `,
      [search || '']
    )) as { id: string }[]

    const recipes = await Recipe.find({
      relations: ['user'],
      where: {
        id: In(recipesId.map(({ id }) => id))
      }
    })

    return res.json(recipes)
  }
}
