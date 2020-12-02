import {
  MigrationInterface,
  QueryRunner,
  Table,
  TableForeignKey
} from 'typeorm'

export class RecipeActionMigration1605213215652 implements MigrationInterface {
  name = 'RecipeActionMigration1605213215652'

  tableName = 'recipe_action'

  foreignKeys = [
    new TableForeignKey({
      columnNames: ['user_id'],
      referencedColumnNames: ['id'],
      referencedTableName: 'user',
      onDelete: 'CASCADE',
      onUpdate: 'CASCADE'
    }),
    new TableForeignKey({
      columnNames: ['recipe_id'],
      referencedColumnNames: ['id'],
      referencedTableName: 'recipe',
      onDelete: 'CASCADE',
      onUpdate: 'CASCADE'
    })
  ]

  public async up(queryRunner: QueryRunner): Promise<void> {
    await queryRunner.createTable(
      new Table({
        name: this.tableName,
        columns: [
          {
            name: 'action',
            type: 'varchar',
            length: '30',
            isPrimary: true
          },
          {
            name: 'created_at',
            type: 'timestamp',
            default: 'now()'
          },
          {
            name: 'user_id',
            type: 'varchar',
            isPrimary: true
          },
          {
            name: 'recipe_id',
            type: 'varchar',
            isPrimary: true
          }
        ]
      })
    )

    await queryRunner.createForeignKeys(this.tableName, this.foreignKeys)
  }

  public async down(queryRunner: QueryRunner): Promise<void> {
    await queryRunner.dropTable(this.tableName)
  }
}
