#version 120

uniform sampler2D texture;
uniform vec2 texelSize;
uniform vec2 direction;

uniform int count;
uniform float sigmas[100];
uniform float add[100];

varying vec2 uv;

void main(){
    vec4 color = vec4(0.0);
    float totalWeight = sigmas[0];
    color += texture2D(texture, uv) * sigmas[0];

    for(int i = 1; i <= count; i++) {
        vec2 offset = texelSize * direction * add[i];
        vec4 smpl1 = texture2D(texture, uv + offset);
        vec4 smpl2 = texture2D(texture, uv - offset);
        float weight = sigmas[i];
        color += (smpl2 + smpl1) * weight;
        totalWeight += weight * 2.0;
    }

    gl_FragColor = color / totalWeight;
}
