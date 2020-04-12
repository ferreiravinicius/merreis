export enum Recurrency {
  UNIQUE = "Unique",
  ANNUAL = "Annual", 
  MONTHLY = "Monthly"
}

export interface Group {
  id?: number
  name: string
}

export interface Tag {
  id?: number
  name?: string
  groups: Group[]
}

export interface Expense {
  tags?: Tag[]
  value?: number
  group?: Group | null
  description?: string
  recurrency?: Recurrency | null
}
