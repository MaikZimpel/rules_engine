json.array!(@rules) do |rule|
  json.extract! rule, :name, :java_class
  json.url rule_url(rule, format: :json)
end
