import {
  Entity,
  Column,
  CreateDateColumn,
  DeleteDateColumn,
  BaseEntity,
  PrimaryColumn
} from 'typeorm'

@Entity()
export default class Image extends BaseEntity {
  @PrimaryColumn()
  id: string

  @CreateDateColumn()
  createdAt: Date

  @DeleteDateColumn()
  deletedAt: Date

  @Column({ length: 255 })
  name: string

  @Column({ length: 255 })
  originalName: string
}
