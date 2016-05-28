package com.innominds.config;


//@Configuration
public class MethodAuthenticationConfig { // extends GlobalMethodSecurityConfiguration
    // {
    //
    // @Autowired
    // AuthenticationManager authenticationManager;
    //
    // @Override
    // public AuthenticationManager authenticationManager() throws Exception {
    // return authenticationManager;
    // }
    //
    // @Override
    // @Bean
    // public AccessDecisionManager accessDecisionManager() {
    // return new AccessDecisionManager() {
    //
    // @Override
    // public boolean supports(Class<?> clazz) {
    // System.out.println("  supports");
    // return true;
    // }
    //
    // @Override
    // public boolean supports(ConfigAttribute attribute) {
    // System.out.println("  supports");
    // return true;
    // }
    //
    // @Override
    // public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
    // InsufficientAuthenticationException {
    //
    // System.out.println("decide");
    //
    // }
    // };
    // }
    //
    // @Override
    // public void setMethodSecurityExpressionHandler(List<MethodSecurityExpressionHandler> handlers) {
    //
    // System.err.println(" setMethodSecurityExpressionHandler");
    // }
    //
    // @Override
    // public PreInvocationAuthorizationAdvice preInvocationAuthorizationAdvice() {
    //
    // return new PreInvocationAuthorizationAdvice() {
    //
    // @Override
    // public boolean before(Authentication authentication, MethodInvocation mi, PreInvocationAttribute preInvocationAttribute) {
    // System.err.println("$$$$        before");
    // return true;
    // }
    // };
    // }
    //
    // @Override
    // public MethodInterceptor methodSecurityInterceptor() throws Exception {
    // // TODO Auto-generated method stub
    // return new MethodInterceptor() {
    //
    // @Override
    // public Object invoke(MethodInvocation i) throws Throwable {
    // System.out.println("method " + i.getMethod() + " is called on " + i.getThis() + " with args " + i.getArguments());
    // final Object ret = i.proceed();
    // System.out.println("method " + i.getMethod() + " returns " + ret);
    // return ret;
    // }
    // };
    // }
    //
    // @Override
    // protected AfterInvocationManager afterInvocationManager() {
    // return new AfterInvocationManager() {
    //
    // @Override
    // public boolean supports(Class<?> clazz) {
    // System.err.println("supports");
    // return true;
    // }
    //
    // @Override
    // public boolean supports(ConfigAttribute attribute) {
    // System.err.println("supports");
    // return true;
    // }
    //
    // @Override
    // public Object decide(Authentication authentication, Object object, Collection<ConfigAttribute> attributes, Object returnedObject)
    // throws AccessDeniedException {
    //
    // System.err.println("decide  ");
    // return null;
    // }
    // };
    // }
    //
    // @Override
    // protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
    // // TODO Auto-generated method stub
    // return new MethodSecurityMetadataSource() {
    //
    // @Override
    // public boolean supports(Class<?> clazz) {
    // // TODO Auto-generated method stub
    // return false;
    // }
    //
    // @Override
    // public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public Collection<ConfigAttribute> getAllConfigAttributes() {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public Collection<ConfigAttribute> getAttributes(Method method, Class<?> targetClass) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    // };
    //
    // }
}
