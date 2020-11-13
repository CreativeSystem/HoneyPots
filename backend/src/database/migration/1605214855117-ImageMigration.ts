import { MigrationInterface, QueryRunner, Table } from 'typeorm'

export class ImageMigration1605214855117 implements MigrationInterface {
  name = 'ImageMigration1605214855117'

  tableName = 'image'

  public async up(queryRunner: QueryRunner): Promise<void> {
    await queryRunner.createTable(
      new Table({
        name: this.tableName,
        columns: [
          {
            name: 'id',
            type: 'varchar',
            isPrimary: true
          },
          {
            name: 'created_at',
            type: 'timestamp',
            default: 'now()'
          },
          {
            name: 'deleted_at',
            type: 'timestamp',
            isNullable: true
          },
          {
            name: 'name',
            type: 'varchar',
            length: '255'
          },
          {
            name: 'original_name',
            type: 'varchar',
            length: '255'
          }
        ]
      })
    )
  }

  public async down(queryRunner: QueryRunner): Promise<void> {
    await await queryRunner.dropTable(this.tableName)
  }
}
