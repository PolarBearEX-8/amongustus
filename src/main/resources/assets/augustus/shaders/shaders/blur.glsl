#version 120

uniform sampler2D texture;
uniform vec2 texelSize;
uniform vec2 direction;

uniform int count;
uniform float[100] sigmas;
uniform float[100] add;

varying vec2 uv;

#define step texelSize * direction

void main(){
    vec4 color = vec4(0.0);
    float totalWeight = sigmas[0];
    vec2 offset = vec2(0.0);
    color += texture2D(texture, uv) * sigmas[0];
    for(int i = 1; i <= count; i++) {
        vec2 offset = step * add[i];
        vec4 smpl1 = texture2D(texture, uv + offset);
        vec4 smpl2 = texture2D(texture, uv - offset);
        float weight = sigmas[i];
        color += (smpl2 + smpl1) * weight;
        totalWeight += weight * 2;
    }
    color /= totalWeight;
    gl_FragColor = color;
}
