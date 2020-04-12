import { Option } from './../components/forms/USelect';

export function str(val: any): string {
  console.log(val.toString())
  return val.toString();
}

export function toOption<T>(item: T, label: string | undefined): Option<T> {
  return {
    item,
    label: label || ""
  }
}