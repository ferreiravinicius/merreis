import { FormControl } from "@material-ui/core";
import { AutocompleteProps, UseAutocompleteProps } from "@material-ui/lab";
import React from "react";
export interface Props {
  name: string;
  label?: string;
}

export type USelectProps<T> = Omit<AutocompleteProps<T>, "renderInput"> &
  UseAutocompleteProps<T> &
  Props;

function USelect<T>({ name, label, ...props }: USelectProps<T>): JSX.Element {
  return (
    <FormControl fullWidth>
      {/* <Autocomplete<T>
        renderInput={(params: RenderInputParams) => (
          <UInput label={label} {...params} />
        )}
        {...props}
      /> */}
    </FormControl>
  );
}

export default USelect;
