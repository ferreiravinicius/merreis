import FormControl from "@material-ui/core/FormControl";
import { Autocomplete, AutocompleteChangeDetails, AutocompleteChangeReason, RenderInputParams, UseAutocompleteProps } from "@material-ui/lab";
import React from "react";
import { call } from "../../commons/utils";
import useSetter, { UseSetterProps } from "../../hooks/useSetter";
import UInput from "./UInput";

export interface Props {
  label?: string;
  setter?: UseSetterProps<any>;
  propLabel?: string;
}

export type UAutocompleteProps<T> = UseAutocompleteProps<T> & Props;

const UAutocomplete = <T extends {}>({
  label,
  setter,
  onChange,
  ...props
}: UAutocompleteProps<T>): JSX.Element => {
  const [handleSetter] = useSetter(setter);

  const handleChange = (
    event: React.ChangeEvent<{}>,
    value: any,
    reason: AutocompleteChangeReason,
    details?: AutocompleteChangeDetails<any>
  ) => {
    call(handleSetter, value);
    call(onChange, event, value, reason, details);
  };

  return (
    <FormControl fullWidth>
      <Autocomplete<T>
        renderInput={(params: RenderInputParams) => (
          <UInput label={label} {...params} />
        )}
        onChange={handleChange}
        {...props}
      />
    </FormControl>
  );
};

export default UAutocomplete;
