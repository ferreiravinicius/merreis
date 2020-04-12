export interface Group {
  id?: number
  name?: string
}

export interface Tag {
  id?: number
  name?: string
  groups: Group[]
}

export interface Expense {
  description?: string
  group?: Group | null
  tags?: Tag[]
}
